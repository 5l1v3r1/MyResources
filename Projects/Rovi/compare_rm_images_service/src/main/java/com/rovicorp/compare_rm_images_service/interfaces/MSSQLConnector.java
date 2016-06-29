/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rovicorp.compare_rm_images_service.interfaces;

import java.sql.ResultSet;

/**
 * @author jappeah
 */

/*
 * 	-Set up mssql connection
 * 	-Execute a provided query
 * 	-Make a result set available
 */

public interface MSSQLConnector {
    public void openConnection() throws Exception;
    public void closeConnection() throws Exception;
    public ResultSet executeRequest() throws Exception;
}
