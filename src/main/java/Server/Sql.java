package Server;


import com.mysql.jdbc.CommunicationsException;

import javax.swing.*;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;


public class Sql {

    private static Connection connect;
    public static boolean serverIsOffline=true;

    public static void delete_statement(){

        String sqldelete = "delete from student where h between ? and ? and name = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(sqldelete);

            pst.setString(1, "180");
            pst.setString(2, "190");
            pst.setString(3, "effi");

            pst.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update_statement(){

        String sqlupdate = "UPDATE student SET name=?  WHERE id =? ";

        try {
            PreparedStatement pst = connect.prepareStatement(sqlupdate);

            pst.setString(1, "effi");
            pst.setString(2, "3344");
            //	pst.setString(3, "66127762");

            pst.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void register(String password,String user){



        if(!serverIsOffline)
        {
            String sqlInsert = "INSERT INTO users (password, username) VALUES ('"+password+"','"+user+"')";

            try {
                PreparedStatement pst = connect.prepareStatement(sqlInsert);
                pst.execute();



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static String [] addAllOnlineUsers(String user) {

            if(!serverIsOffline){
                try {
                    ArrayList<String >temp=new ArrayList<>();
                    PreparedStatement statement = connect.prepareStatement("SELECT * FROM users");

                    ResultSet result = statement.executeQuery();
                    while (result.next()) {

                        if (!result.getString(1).equals(user)&&result.getString(3).equals("true"))
                        {

                            temp.add(result.getString(1));

                        }


                    }


                    String[] names = new String[temp.size()];
                    names = temp.toArray(names);
                    return names;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
                }

        return null;
    }

    public static void setTheUserStatistics(String user){
        PreparedStatement statement = null;


        try {
            statement = connect.prepareStatement("INSERT INTO statistics (id,win, lose, total ) VALUES ('"+user+"'"+",0,0,0)");

            System.out.println(statement);

             statement.executeUpdate();







        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Integer> getTheStatisticsdata(String userName){
        PreparedStatement statement = null;

        ArrayList<Integer> list=new ArrayList();

        try {
            statement = connect.prepareStatement("SELECT * FROM statistics");


            ResultSet statistics = statement.executeQuery();


            while (statistics.next())
            {
                if(statistics.getString(1).equals(userName))
                {
                    list.add(Integer.parseInt(statistics.getString(2)));
                    list.add(Integer.parseInt(statistics.getString(3)));
                    list.add(Integer.parseInt(statistics.getString(4)));

                }

            }
            System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
return list;

    }

    public  static void setTheUserOnline(String online,String username)
    {



        if(!serverIsOffline)
        {
            String sqlInsert =  "UPDATE users SET isonline = '"+online+"'  WHERE username = '"+username+"'";

            try {
                PreparedStatement pst = connect.prepareStatement(sqlInsert);
                pst.execute();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean checkIfPasswordIsTrue(String user,String password){

        if(!serverIsOffline)
            try
            {
                PreparedStatement userStatment = connect.prepareStatement("SELECT username FROM users");

                ResultSet result = userStatment.executeQuery();

                PreparedStatement passwordStatment = connect.prepareStatement("SELECT password FROM users");

                ResultSet resultPassword = passwordStatment.executeQuery();

                while(result.next()&&resultPassword.next())
                {

                    if(result.getString(1).equals(user)&&resultPassword.getString(1).equals(password))
                        return true;


                }

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        return false;

    }

    public static boolean checkIfUserExist(String user)
        {

            if(!serverIsOffline)
        try {
            PreparedStatement statement = connect.prepareStatement("SELECT username FROM users");

            ResultSet result = statement.executeQuery();

            while(result.next())
            {

                if(result.getString(1).equals(user))
                    return true;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;
    }

    private static void connection()
    {

        try {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void ConectingToSQL ()
    {

        connection();

        String host = "jdbc:mysql://192.168.1.20:3306/wargame?allowPublicKeyRetrieval=true&?useSSL=false";
        String username = "jeffrey";
        String password = "1234";


        try {


            connect = DriverManager.getConnection(host, username, password);
            serverIsOffline=false;

        } catch (SQLException e) {
            serverIsOffline=true;
            JOptionPane.showMessageDialog(null,"server is offline right now ");
            e.printStackTrace();
        }


    }


    public Sql(){
        ConectingToSQL();

    }
}
