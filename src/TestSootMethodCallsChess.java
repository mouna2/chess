

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import soot.*;
import soot.baf.MethodArgInst;
import soot.jimple.FieldRef;
import soot.jimple.InvokeExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.internal.AbstractDefinitionStmt;
import soot.jimple.internal.AbstractInvokeExpr;
import soot.jimple.internal.JAssignStmt;
import soot.jimple.spark.SparkTransformer;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Targets;
import soot.options.Options;
import soot.util.Chain;

public class TestSootMethodCallsChess extends SceneTransformer {

	static LinkedList<String> excludeList;
	
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final static String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final static String password = "root";

	/** The name of the computer running MySQL */  
	
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "databasechess";
	
	/** The name of the table we are testing with */
	private final String tableName = "classes";
	

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("root", userName);
		connectionProps.put("123456", password);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasechess"+"?useLegacyDatetimeCode=false&serverTimezone=UTC","root","123456");

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 * @throws IOException 
	 */
	public void run() throws IOException {
		ResultSet rs = null; 
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}



		 
		   
		   
		
	    
		
	}
	
	public static void main(String[] args) throws SQLException	{

		 String mypath="de.java_chess"; 
		 String directory= "C:\\Users\\mouna\\git\\chess\\java-chess\\src"; 
		 
		 Connection conn=getConnection();
		Statement st= conn.createStatement();
		Statement st2= conn.createStatement();
		Statement st3= conn.createStatement();

		Statement st4= conn.createStatement();
		Statement st5= conn.createStatement();

		 
		 
		 
			    excludeJDKLibrary();

//		
			 
			 
			 //set classpath
	    String javapath = System.getProperty("java.class.path");
	    String jredir = System.getProperty("java.home")+"\\lib\\rt.jar";
	    String path = javapath+File.pathSeparator+jredir+File.pathSeparator+directory ;
	 
	    Scene.v().setSootClassPath(path);
	    System.out.println(path);
	    TestSootMethodCallsChess analysis = new TestSootMethodCallsChess();
	    PackManager.v().getPack("wjtp").add(new Transform("wjtp.TestSootCallGraph", analysis));
	    
	    
	    System.out.println(path);
            //add an intra-procedural analysis phase to Soot

	    excludeJDKLibrary();


	    //whole program analysis
	    Options.v().set_whole_program(true);

            //load and set main class
	    Options.v().set_app(true);

	    Scene.v().loadDynamicClasses();

	   List<String> mylist = Arrays.asList(directory); 
//	   System.out.println(mylist);
	    Options.v().set_process_dir(mylist);
	    Scene.v().loadDynamicClasses();
		 System.out.println("hey");
//
        Scene.v().loadNecessaryClasses();
//        Scene.v().loadBasicClasses();

	    System.out.println("Application classes"+Scene.v().getApplicationClasses());
	    System.out.println("classes "+Scene.v().getEntryPoints());
//	    System.out.println("library classes "+Scene.v().getLibraryClasses());
	    System.out.println("dynamic classes"+Scene.v().dynamicClasses());
	    System.out.println("classes"+Scene.v().getClasses());

	    System.out.println("****************");
	    //whole program analysis
	    Options.v().set_whole_program(true);

            //load and set main class
	    Options.v().set_app(true);
	    Scene.v().loadDynamicClasses();
	   System.out.println(mylist);
	    Options.v().set_process_dir(mylist);
//	    Scene.v().loadDynamicClasses();
        Scene.v().loadNecessaryClasses();
//        Scene.v().loadBasicClasses();
	    
	    System.out.println("Application classes"+Scene.v().getApplicationClasses());
	    System.out.println("classes "+Scene.v().getEntryPoints());
//	    System.out.println("library classes "+Scene.v().getLibraryClasses());
	    System.out.println("dynamic classes"+Scene.v().dynamicClasses());
	    System.out.println("classes"+Scene.v().getClasses());

	    System.out.println("****************");
//	    SootClass appclass = Scene.v().loadClassAndSupport(mainclass); 
//	    appclass.setApplicationClass();
//	    System.out.println("APPCLASS ==="+appclass.isPhantom()+" level "+appclass.resolvingLevel());
//        Scene.v().addBasicClass("Example", SootClass.BODIES);
//
//
//	   
//	    System.out.println("-------------");
//	    
//	    
//	    Scene.v().setMainClass(appclass);
//	    Scene.v().addBasicClass(mainclass);

	    Scene.v().loadNecessaryClasses();
	    Scene.v().loadDynamicClasses(); 
	    Scene.v().loadBasicClasses(); 
	    Scene.v().getActiveHierarchy(); 
	    
	    List<String> methodcallsList = new ArrayList<String>(); 

	    List<String> localList = new ArrayList<String>(); 
	    System.out.println("**********************************");
	    Chain<SootClass> sootclasses =  Scene.v().getClasses();
	    
	   int counter=0;
	   try {
		   
		   
	    for(SootClass callerclass: sootclasses) {
	    	System.out.println("oooooooooooooooooooo"+counter);
//	    	 for ( SootClass callerclass: Scene.v().getClasses()) {
//	    		 System.out.println(callerclass);
//	 	    	System.out.println(myclass.getPackageName());
	 	    	
	 		    	System.out.println("--------------------------------------");

//	 		    	System.out.println(callerclass);
	 		    	System.out.println("--------------------------------------");
	 		    	 String classname = null; String classid=null; 
	 		    	 String mycallerclass=callerclass.toString(); 
	 		    	ResultSet classes = st.executeQuery("SELECT * from classes where classname='"+mycallerclass+"'"); 
	 				while(classes.next()){
	 					  classname = classes.getString("classname"); 
	 					  classid= classes.getString("id"); 
	 					 System.out.println(classname +" "+classid);
	 		   		   }		

	 		    	for(SootMethod mymethod: callerclass.getMethods()) {
//	 			    	System.out.println("//////////////////////////////////////////////////");

						  System.out.println(mymethod+"   "+callerclass);

	 		    		String methodname = null; 
	 		    		String methodid=null; 
	 		    		String methodparams= mymethod.getParameterTypes().toString().replaceAll("\\s+",""); 
	 		    		methodparams= methodparams.replaceAll("\\[", "("); 
	 		    		methodparams= methodparams.replaceAll("\\]", ")"); 
	 		    		methodname = mymethod.getName(); 
	 		    		if(methodname.equals("<init>")) methodname="-init-"; 
	 		    		ResultSet methods = st.executeQuery("SELECT * from methods where methodname='"+methodname+methodparams+"'and classname='"+classname+"'"); 
	 					while(methods.next()){
	 						  methodname = methods.getString("methodname"); 
	 						  methodid= methods.getString("id"); 
	 						  System.out.println(methodname +" "+methodid);

	 			   		   }	
	 		    		
	 		    		Body body = null; 
	 		    		try {
	 			    		 body = mymethod.retrieveActiveBody(); 
	 		    		}catch(Exception e) {
//	 		    			throw e; 
	 		    		}
	 					
	 					
	 					
	 					
	 		    		if(body!=null && ( counter!=1728 ||counter!=1727|| counter!=1729)) {
	 		    			
	 		    			
	 		    			 PatchingChain<Unit> units = body.getUnits();
	 			    			
	 		    			 for(Unit unit: units) {
	 		    				 System.out.println("UNIT"+unit.toString());
//	 		    				 if(unit instanceof InvokeExpr){
	 		    					 System.out.println(unit);
	 		    					 System.out.println("========================>>>> " +unit);
									Value right = null;
									if(unit instanceof JAssignStmt)
	 		    						 right=((JAssignStmt) unit).getRightOp(); 
										System.out.println(right);
										if(right instanceof InvokeExpr) {
											 SootClass calleeClass = ((InvokeExpr) right).getMethodRef().getDeclaringClass(); 
											 String calleeMethodName = ((InvokeExpr) right).getMethodRef().getName()+((InvokeExpr) right).getMethodRef().getParameterTypes(); 
											 System.out.println(calleeMethodName);
											 System.out.println("----");
											 if(calleeClass.toString().contains("chess")) {
												String callerMethod= mymethod.getName()+mymethod.getParameterTypes(); 
												
												
												callerMethod = callerMethod.replaceAll("\\s+",""); 
												calleeMethodName = calleeMethodName.replaceAll("\\s+",""); 
												callerMethod = callerMethod.replaceAll("\\[","("); 
												callerMethod = callerMethod.replaceAll("\\]",")"); 
												
												calleeMethodName = calleeMethodName.replaceAll("\\[","("); 
												calleeMethodName = calleeMethodName.replaceAll("\\]",")");
												
												System.out.println("CALLER METHOD: "+callerMethod); 
												System.out.println("CALLER CLASS: "+mycallerclass); 
												System.out.println("CALLEE METHOD: "+calleeMethodName); 
												System.out.println("CALLEE CLASS: "+calleeClass); 
												
												System.out.println("OVER");
												ResultSet callers = st2.executeQuery("SELECT * from methods where methodname='"+callerMethod+"'and classname='"+mycallerclass+"'"); 
												 String callerMethodName =null; 
												 String callerMethodID = null; 							
												 String callerClassID=null; 

												while(callers.next()){
							 						   callerMethodName = callers.getString("methodname"); 
							 						   callerMethodID = callers.getString("id"); 
							 						  callerClassID = callers.getString("classid"); 

							 						  System.out.println(callerMethodName +" "+callerMethodID);

							 			   		   }	
												
												
												
												
												
												
												ResultSet callees = st4.executeQuery("SELECT * from methods where methodname='"+calleeMethodName+"'and classname='"+calleeClass+"'"); 
												 String calleeMethodID = null; 
												 String calleeClassID=null; 
												while(callees.next()){
							 						   calleeMethodName = callees.getString("methodname"); 
							 						   calleeMethodID = callees.getString("id"); 
														calleeClassID = callees.getString("classid"); 

							 						  System.out.println(calleeMethodName +" "+calleeMethodID);

							 			   		   }
												
												
												 String callingrelationship=callerMethodName+"-"+callerMethodID+"-"+calleeMethodName+"-"+calleeMethodID; 
				    								if(!methodcallsList.contains(callingrelationship) &&callerMethodID!=null && calleeMethodID!=null && calleeMethodID!=null && calleeClassID!=null) {
														
				    									String fullcaller= mycallerclass+"."+callerMethodName; 
				    									String fullcallee= calleeClass+"."+calleeMethodName; 
				    									String statement= "INSERT INTO `sootmethodcalls`(`callermethodid`, `callername`,`callerclass`, `callerclassid`, `fullcaller`, "
																+ "`calleemethodid`,`calleename`, `calleeclass`, `calleeclassid`, `fullcallee`)"
																+ "VALUES ('"+callerMethodID +"','" +callerMethodName +"','" +mycallerclass+"','" +callerClassID+"','" 
																+fullcaller+"','"+calleeMethodID+"','" +calleeMethodName+"','"+calleeClass+"','"+calleeClassID+"','"+fullcallee+"')";  
														System.out.println(statement);
														st5.executeUpdate(statement);
														methodcallsList.add(callingrelationship); 

													}
												
												
											 }
										}
	 		    					 
//	 		    				 }	
	 		    			

	 			    		
 				    	}
	 		    		}
		    		
	 		    	}
	 		    	
	 				
	 		    	System.out.println("hey");
	 	    	
	 		    	counter++;
	 	    
	    
	    
	    
	    } 
	    
	   }catch (Exception e ) {
	 	    
	 	    }
	    
	   
	    System.out.println("over");
	    //enable call graph
	    //enableCHACallGraph();
	    //enableSparkCallGraph();

            //start working
//	    PackManager.v().runPacks();
	    System.out.println("over32");
	    st.close();
	}
	private static void excludeJDKLibrary()
	{
		 //exclude jdk classes
	    Options.v().set_exclude(excludeList());
		  //this option must be disabled for a sound call graph
	      Options.v().set_no_bodies_for_excluded(true);
	      Options.v().set_allow_phantom_refs(true);
	}
	private static void enableSparkCallGraph() {

		//Enable Spark
	      HashMap<String,String> opt = new HashMap<String,String>();
	      //opt.put("propagator","worklist");
	      //opt.put("simple-edges-bidirectional","false");
	      opt.put("on-fly-cg","true");
	      //opt.put("set-impl","double");
	      //opt.put("double-set-old","hybrid");
	      //opt.put("double-set-new","hybrid");
	      //opt.put("pre_jimplify", "true");
	      SparkTransformer.v().transform("",opt);
	      PhaseOptions.v().setPhaseOption("cg.spark", "enabled:true");
	}

	private static void enableCHACallGraph() {
		CHATransformer.v().transform();
	}

	private static LinkedList<String> excludeList()
	{
		if(excludeList==null)
		{
			excludeList = new LinkedList<String> ();

			excludeList.add("java.");
		    excludeList.add("javax.");
		    excludeList.add("sun.");
		    excludeList.add("sunw.");
		    excludeList.add("com.sun.");
		    excludeList.add("com.ibm.");
		    excludeList.add("com.apple.");
		    excludeList.add("apple.awt.");
		}
		return excludeList;
	}
	@Override
	protected void internalTransform(String phaseName,
			Map options) {

//		int numOfEdges =0;
//	    enableSparkCallGraph();
//		CallGraph callGraph = Scene.v().getCallGraph();
//		for(SootClass sc : Scene.v().getApplicationClasses()){
//			for(SootMethod m : sc.getMethods()){
//
//		Iterator<MethodOrMethodContext> targets = new Targets(
//				 callGraph.edgesOutOf(m));
//
//				 while (targets.hasNext()) {
//
//					 numOfEdges++;
//
//				SootMethod tgt = (SootMethod) targets.next();
//				 System.out.println(m + " may call " + tgt);
//				 }
//			}
//		}
//
//		 System.err.println("Total Edges:" + numOfEdges);

		 
		 
		 
	      
	}
}