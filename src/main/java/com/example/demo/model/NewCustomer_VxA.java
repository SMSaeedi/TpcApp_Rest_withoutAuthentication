package com.example.demo.model;


import com.example.demo.dataBaseConfig.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewCustomer_VxA {

    public NewCustomerOutput process(NewCustomerInput input) {
        NewCustomerOutput nco = new NewCustomerOutput();
        String pm = input.getPaymentMethod();
        if (pm != null) {
            if (true) {
                Pov pov = new Pov();
                LongWrapper wrapper = pov.call("" + input.getPoId(), input.getBusinessName());
                wrapper.getValue().getClass();
                Connection conn = Database.pickConnection();
                try {
                    if (conn != null) {
                        long addr_id_temp = getAddressID(conn, input);
                        if (addr_id_temp == -1) {
                            addr_id_temp = createAddress(conn, input);
                            if (addr_id_temp == -1) {
                                throw new RuntimeException("DB Error: Couldn't create Address ID");
                            }
                        }

                        long c_id = insertCostumer(conn, input, addr_id_temp);
                        if (c_id != -1) {
                            nco.setCustomerId(c_id);
                        } else {
                            throw new RuntimeException("DB Error: Couldn't insert Costumer");
                        }
                    }
                    conn.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    Database.relaseConnection(conn);
                }
            }
        }
        return nco;
    }

    private synchronized long getAddressID(Connection conn, NewCustomerInput input)
            throws SQLException {
        long addId = -1;
        long addr_co_id = getCOID(conn, input);
        if (addr_co_id != -1) {
            String ba1 = input.getBillingAddr1();
            String ba2 = input.getBillingAddr2();
            String bc = input.getBillingCity();
            String bs = input.getBillingState();
            String bz = input.getBillingZip();
            String sql = null;
            if ((ba1 != null) && (ba2 != null) && (bc != null) && (bs != null) && (bz != null)) {
                String CR = System.getProperty("line.separator");
                sql = "select addr_id from address" +
                        " where lower(ADDR_STREET1) = '" + ba1.toLowerCase().trim() + "'" +
                        " and lower(ADDR_STREET2) = '" + ba2.toLowerCase().trim() + "'" +
                        " and lower(ADDR_CITY) = '" + bc.toLowerCase().trim() + "'" +
                        " and lower(ADDR_STATE) = '" + bs.toLowerCase().trim() + "'" +
                        " and lower(ADDR_ZIP) = '" + bz.toLowerCase().trim() + "'" +
                        " and ADDR_CO_ID = " + addr_co_id;
            }
            Statement stmt = Database.createStatement(conn);
            ResultSet rs = stmt.executeQuery(sql);
            boolean v = rs.next();
            if (v) {
                addId = rs.getLong(1);
            }
            if (rs != null) {
                rs.close();
            }
            stmt.close();
        }
        return (addId);
    }

    private synchronized long createAddress(Connection conn, NewCustomerInput input)
            throws SQLException {
        long addr_id = -1;
        String sql = "";
        long addr_co_id = getCOID(conn, input);
        Statement stmt = Database.createStatement(conn);
        if (addr_co_id != -1) {
            sql = "select max(addr_id) from address";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                addr_id = rs.getLong(1);
            }
            addr_id++;
            sql = "INSERT INTO ADDRESS( ADDR_ID, ADDR_STREET1, ADDR_STREET2, ADDR_CITY, " +
                    "ADDR_STATE, ADDR_ZIP, ADDR_CO_ID)" +
                    " VALUES( " + addr_id + ", '" +
                    input.getBillingAddr1() + "', '" +
                    input.getBillingAddr2() + "', '" +
                    input.getBillingCity() + "', '" +
                    input.getBillingState() + "', '" +
                    input.getBillingZip() + "', " +
                    addr_co_id + ")";
            //
            int rc = stmt.executeUpdate(sql);
            if (rs != null) {
                rs.close();
            }
        }
        stmt.close();
        return (addr_id);
    }

    private long getCOID(Connection conn, NewCustomerInput input) throws SQLException {
        long res = -1;
        String sql = "select co_id from country where lower(co_name) = lower('" +
                input.getBillingCountry() + "')";
        Statement stmt = Database.createStatement(conn);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            res = rs.getLong(1);
        }
        if (rs != null) {
            rs.close();
        }
        stmt.close();
        return (res);
    }

    private synchronized long insertCostumer(Connection conn, NewCustomerInput input, long addr_id_temp) throws SQLException {
        long c_id = -1;
      /*  Lcg64RngTools tools = new Lcg64RngTools(System.currentTimeMillis());
        int discount = tools.Next(0, 50);
        String sql = "";
        Statement stmt = Database.createStatement(conn);
        sql = "SELECT max(C_ID) FROM customer";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            c_id = rs.getLong(1);
        }
        c_id++;
        sql = "INSERT INTO CUSTOMER(C_ID, C_BUSINESS_NAME, C_BUSINESS_INFO, C_PASSWD, C_CONTACT_FNAME, C_CONTACT_LNAME," + " C_CONTACT_PHONE," + " C_CONTACT_EMAIL," + " C_PAYMENT_METHOD," + " C_CREDIT_INFO," + " C_DISCOUNT," + " C_ADDR_ID," + " C_PO)" +
                " VALUES" + "( " +
                c_id + ", '" +
                input.getBusinessName() + "', '" +
                input.getBusinessInfo() + "', '" +
                input.getPassword() + "', '" +
                input.getContactFName() + "', '" +
                input.getContactLName() + "', '" +
                input.getContactPhone() + "', '" +
                input.getContactEmail() + "', '" +
                input.getPaymentMethod() + "', '" +
                input.getCreditInfo() + "', " +
                discount + ", " +
                addr_id_temp + ", " +
                input.getPoId() + ")";
        //stmt197
        int rc = stmt.executeUpdate(sql);
        if (rs != null) {
            rs.close();
        }
        //ps close
        stmt.close();*/
        return (c_id);
    }
}
