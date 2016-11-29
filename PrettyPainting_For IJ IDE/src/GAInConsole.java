//import java.awt.print.Printable;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.sql.*;
//import java.util.Arrays;
//public class GAInConsole {
//	static final String WRITE_OBJECT_SQL = "INSERT INTO genimage(idv_value) VALUES (?)";
//	static final String READ_OBJECT_SQL = "SELECT * FROM genimage WHERE id=(?)";
//
//	DataBaseUtil db;
//	public GAInConsole()
//	{
//		db = new DataBaseUtil();
//
//	}
//	public void dbtest()
//	{
//
//
//		String sql = "SELECT * FROM genimage";
//
//		ResultSet result = db.query(sql);
//
//		try {
//			while(result.next()) {
//			    System.out.println("userNname:" + result.getString(1)
//			            + ", password:" + result.getString(2));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//db.disconnect();
//	}
//
//
//	public void storeIndividual()
//	{
//
//		Individual idv =new Individual();
//		idv.increaseFitness();
//		ByteArrayOutputStream outIndividual = new ByteArrayOutputStream();
//		ObjectOutputStream outputStream=null;
//		try {
//			outputStream = new ObjectOutputStream(outIndividual);
//			outputStream.writeObject(idv);
//
//
//			byte[] idv_bin=outIndividual.toByteArray();
//
//			ByteArrayInputStream bais =
//	                new ByteArrayInputStream(idv_bin);
//
//			PreparedStatement preparedStatement = db.con.prepareStatement(WRITE_OBJECT_SQL);
//
//			preparedStatement.setBinaryStream(1, bais,(long)idv_bin.length);
//			preparedStatement.executeUpdate();
//			preparedStatement.close();
//			//db.disconnect();
//
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//
//
//
//
//	}
//
//	public void getIndividual()
//	{
//		ByteArrayInputStream bais;
//        ObjectInputStream in = null;
//        Individual idv_read = null ;
//		try{
//
//		PreparedStatement preparedStatement = db.con.prepareStatement(READ_OBJECT_SQL);
//		preparedStatement.setInt(1, 16);
//		ResultSet rsResultSet = preparedStatement.executeQuery();
//		while(rsResultSet.next())
//		{
//			Blob idv = rsResultSet.getBlob("idv_value");
//			InputStream ins_idv = idv.getBinaryStream();
//			byte[] buffer = new byte[4096];
//
//			ins_idv.read(buffer);
//
//	        bais = new ByteArrayInputStream(buffer);
//            in = new ObjectInputStream(bais);
//
//            idv_read = (Individual)in.readObject();
//		}
//
//        }catch (Exception e) {
//            //logger.error("bytes2Msg error!", e);
//        }
//
//		System.out.println(idv_read.getFitness());
//	}
//
//
//
//}
