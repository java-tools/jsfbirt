jsf4birt

The jsf4birt component library supports the rendering of BIRT-based reports on a page within JavaServer Faces (JSF) Web applications. Report viewers implemented as JSF components can be easily adjusted through tag attributes.

There are two components. A BIRT wrapper and an Actuate wrapper.

The BIRT wrapper component displays BIRT reports created in BIRT’s Eclipse design tool. This component uses the BIRT runtime API. The component calls the appropriate part of the API from the BIRT runtime to get generated HTML code to put on the page. Everything is done on the server side following the usual JSF lifecycle processing.

The Actuate wrapper component loads reports from an installed Actuate server. The component uses the Actuate JavaScript API to load and run reports on the page using client-side processing. 

REQUIREMENTS

JDK 1.5 or higher
JSF 1.2.x (JSF 2.0 support not completely verified)
BIRT runtime 2.3 or 2.5.
Actuate JavaScript API 2.0

INSTALLING

1. Download the jsf4birt library ZIP.
2. Uncompress the ZIP into it’s constituents:
    - A single JAR file for jsf4birt library
    - The license for using jsf4birt
    - A TLDdocs folder with files containing information about the tags and their use
    - This file
3. Read the license.
4. Use your browser to open the index.html file within the TLDdocs folder.
5. Use the instructions below for further installation.

INSTALLING FOR THE BIRT WRAPPER

1.  Download BIRT runtime library from here: 
http://www.eclipse.org/downloads/download.php?file=/birt/downloads/drops/R-R1-2_5_1-200909220630/birt-runtime-2_5_1.zip  to use v2.5 or http://www.eclipse.org/downloads/download.php?file=/birt/downloads/drops/R-R1-2_3_2_2-200906011507/birt-runtime-2_3_2_2.zip  to use v2.3.
2. Create a /WEB-INF/lib directory underneath the Tomcat webapps directory. 
3. Copy all the JARs in the birt-runtime-[version]/ReportEngine/lib directory from the Report Engine download into your WebReport/WEB-INF/lib directory. 
4. Create a directory named platform in your WEB-INF folder. 
5. Copy the birt-runtime-[version]/Report Engine/plugins and birt-runtime-[version]/ReportEngine/configuration directories to the platform directory you just created. 
6. Add the jsf4birt library to your classpath.

INSTALLING FOR THE ACTUATE WRAPPER

1. Add the jsf4birt library to your classpath.
2. Install and configure an Actuate server. 
3. Configure Actuate server parameters for URL to Actuate server and login credentials into web.xml (or directly on the page through component attributes). Here’s an example inside a web.xml file:

<context-param>
  <param-name>actuate.serverUrl</param-name>
  <param-value>http://localhost:8900/iportal</param-value>
</context-param>
 
<context-param>
   <param-name>actuate.serverLogin</param-name>
   <param-value>administrator</param-value>
</context-param>
 
<context-param>
   <param-name>actuate.serverPassword</param-name>
   <param-value></param-value>
</context-param>
