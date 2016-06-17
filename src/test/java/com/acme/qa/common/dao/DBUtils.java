/*
 * *********************************************************
 *  Copyright (c) 2016 Symantec, Inc.  All rights reserved.
 * *********************************************************
 */
package com.symantec.qa.common.dao;

import com.symantec.qa.common.utilities.db.DatabaseManager;
import com.symantec.qa.common.utilities.db.DatabaseType;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by gabe_zoldi on 06/09/2016 07:11 AM.
 */
public class DBUtils {

    // delegate
    private static ArrayList<Map<String, String>> executeQuery(String query, DatabaseType dbType) {
        return DatabaseManager.executeQuery(query, dbType);
    }

	private static int executeUpdate(String query, DatabaseType dbType) {
        return DatabaseManager.executeUpdate(query, dbType);
    }

    /*
     * database keywords
     */
    public static String getSecurityGUID(String emailAddress) {
        StringBuilder query = new StringBuilder();

        query.append( "SELECT security_guid "                 );
        query.append( "FROM Member_Security_Guid AS msg "     );
        query.append( "INNER JOIN Member AS m "               );
        query.append( "  ON msg.member_id = m.member_id "     );
        query.append( "    AND guid_type = 'PASSWORD_RESET' " );
        query.append( "    AND email_address = '%s' "         );

        String joinselect = String.format( query.toString(), emailAddress.toLowerCase() );

        ArrayList<Map<String, String>> resultset = DBUtils.executeQuery( joinselect, DatabaseType.QA );
        return resultset.get(0).get( "security_guid" );
    }

//    public static int deleteUser(String username) {
//        String query = "DELETE FROM users WHERE user_name = '" + username.toUpperCase() + "'";
//        return DBUtils.executeUpdate(query);
//    }
//
//    public static ArrayList<Map<String, String>> getUser(String username) {
//        String query = "SELECT * FROM users WHERE USER_NAME = '" + username.toUpperCase() + "'";
//        return DBUtils.executeQuery(query);
//    }
//
//    public static int addPermission(String username, String organization, int permissionId) {
//        return DBUtils.setPermission(username, organization, permissionId, "Y");
//    }
//
//    public static int removePermission(String username, String organization, int permissionId) {
//        return DBUtils.setPermission(username, organization, permissionId, "N");
//    }
//
//    private static int setPermission(String user, String org, int permId, String flag) {
//        StringBuilder update = new StringBuilder();
//
//        update.append( "UPDATE userPermission "                                          );
//        update.append( "SET permissionValue = '%s' "                                     );
//        update.append( "WHERE permissionId = %s "                                        );
//        update.append( "  AND userid = ( "                                               );
//        update.append( "                 SELECT userid "                                 );
//        update.append( "                 FROM   users "                                  );
//        update.append( "                 WHERE  username = '%s' "                        );
//        update.append( "               ) "                                               );
//        update.append( "  AND organizationId = ( "                                       );
//        update.append( "                         SELECT organizationId "                 );
//        update.append( "                         FROM   organization "                   );
//        update.append( "                         WHERE  organizationDisplayName = '%s' " );
//        update.append( "                       ) "                                       );
//
//        String updatePermission = String.format(
//                update.toString(),
//                flag, permId, user.toUpperCase(), org );
//
//        return DBUtils.executeUpdate(updatePermission, DatabaseType.MSSQL);
//    }

}
