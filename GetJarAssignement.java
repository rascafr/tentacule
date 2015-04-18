
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class GetJarAssignement {
	
    private static Map<String, String> usernamesMap;

    public static void main(String[] args) {

	ObjectInputStream ois = null;


	JarInputStream jis = null;
	try {
	    System.out.println("Loading : Resources.jar");
	    InputStream is = new URL("http://sirt.eseo.fr/blox_2015/res/resources.jar").openStream();
      
	    jis = new JarInputStream(is);
	    JarEntry entry;
	    while ((entry = jis.getNextJarEntry()) != null) {
		if (entry.getName().equals("assignment")) {

		    jis.closeEntry();
		}
		else if (entry.getName().equals("usernameMap")) {
		    ois = new ObjectInputStream(jis);
		    usernamesMap = ((Map)ois.readObject());
          
		    jis.closeEntry();
		}
		else if (entry.getName().equals("bigLogo")) {

		}
		else if (entry.getName().equals("smallLogo")) {

		}
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
	catch (ClassNotFoundException cnfe) {
	    cnfe.printStackTrace();
	}
   
	finally
	    {
		try
		    {
			jis.close();
		    }
		catch (IOException e) {
		    e.printStackTrace();
		}
	    }


	BufferedWriter writer = null;
	try
	    {
		writer = new BufferedWriter( new FileWriter("namesMap.txt"));
				    

	    }
	catch ( IOException e)
	    {
	    }
				
	int count = 0;
	Iterator iterator = usernamesMap.keySet().iterator();

	while(iterator.hasNext()) {
	    Object key   = iterator.next();
	    Object value = usernamesMap.get(key);
	    //System.out.println("-> " + key + ", " + value);

	    try	{writer.write(key + ", " + value + "\n");count++;} catch ( IOException e) {}
	}


			
	try
	    {
		if ( writer != null)
		    writer.close( );
	    }
	catch ( IOException e)
	    {
	    }
				
	System.out.println("Done : " + count + " names founded.");

    }


}
