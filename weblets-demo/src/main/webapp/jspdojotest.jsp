<%--
    Document   : demojsp1.jsp
    Created on : 14.12.2007, 16:53:29
    Author     : werpu
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean class="net.java.dev.weblets.WebletUtils" scope="application" id="jspweblet" />
<%@ taglib uri="http://weblets.dev.java.net/tags" prefix="weblets" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>

        <style type="text/css">
		@import '<%= jspweblet.getURL( "org.dojotoolkit","/dojo/dojo.css") %>';
		@import '<%= jspweblet.getURL( "org.dojotoolkit","/dijit/tests/css/dijitTests.css") %>';
	</style>
    <script type="text/javascript" src='<%= jspweblet.getURL( "org.dojotoolkit","/dojo/dojo.js" )%>'
		djConfig="parseOnLoad: true, isDebug: true"></script>
	<script type="text/javascript" src='<%= jspweblet.getURL( "org.dojotoolkit","/dijit/tests/_testCommon.js") %>'></script>


    <script type="text/javascript" src='<%= jspweblet.getURL( "org.dojotoolkit","/dijit/Editor.js") %>'></script>
	<script type="text/javascript">
		dojo.require("dijit.Editor");
		dojo.require("dijit._editor.plugins.AlwaysShowToolbar");
		dojo.require("dijit._editor.plugins.EnterKeyHandling");
//		dojo.require("dijit._editor.plugins.FontChoice");  // 'fontName','fontSize','formatBlock'
		dojo.require("dijit._editor.plugins.TextColor");
		dojo.require("dijit._editor.plugins.LinkDialog");
		dojo.require("dojo.parser");	// scan page for widgets and instantiate them
	</script>

    </head>
    <body>
        <p>
        you can mix weblets in the same page, in this example
            we mix the links to our welcome.js file with a complex
            dojo toolkit testcase
        / !!!
        </p>
        <div id="demotext">The demotext here will be replaced by the javascript included below</div>
        <script type="text/javascript" src='<%= jspweblet.getURL( "weblets.demo","/dojotest.js") %>'></script>
       
       <h1 class="testTitle"><label for="editor1">Editor + Plugins Test</label></h1>

	<div style="border: 1px solid black;">
		<div dojoType="dijit.Editor" id="editor1"><p>This instance is created from a div directly with default toolbar and plugins</p></div>
	</div>
	<button onClick="dijit.byId('editor1').destroy()">destroy</button>
	<button onclick="console.log(dijit.byId('editor1').getValue().length)">getValue</button>
	<hr/>
	<div style="border: 1px dotted black;">
		<h3><label for="thud">thud - from textarea</label></h3>
		<textarea dojoType="dijit.Editor" height=""
			extraPlugins="['dijit._editor.plugins.AlwaysShowToolbar']"
			styleSheets='<%= jspweblet.getURL( "org.dojotoolkit","/dojo/resources/dojo.css") %>' id="thud">
			<p>
				This editor is created from a textarea with AlwaysShowToolbar plugin (don't forget to set height="").
			</p>
			<p>
				Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean
				semper sagittis velit. Cras in mi. Duis porta mauris ut ligula. Proin
				porta rutrum lacus. Etiam consequat scelerisque quam. Nulla facilisi.
				Maecenas luctus venenatis nulla. In sit amet dui non mi semper iaculis.
				Sed molestie tortor at ipsum. Morbi dictum rutrum magna. Sed vitae
				risus.
			</p>
		</textarea>
		<h3>..after</h3>
	</div>
	<hr/>
	<div style="border: 1px dotted black;">
		<h3><label for="blah">blah entry</label></h3>
		<textarea dojoType="dijit.Editor"
			plugins="['bold','italic','|','createLink','foreColor','hiliteColor']"
			styleSheets='<%= jspweblet.getURL( "org.dojotoolkit","dojo/resources/dojo.css") %>' id="blah">
			This instance includes optional toolbar buttons which pull in additional ui (dijit) code.
			Note the dojo.require() statements required to pull in the associated editor plugins to make
			this work.
		</textarea>
		<h3>..after</h3>
	</div>
	<hr/>
	<div style="border: 1px dotted black;">
		<h3><label for="blah2">Another blah entry</label></h3>
		<textarea dojoType="dijit.Editor"
			plugins="['bold','italic','|',{name:'dijit._editor.plugins.LinkDialog'}]"
			styleSheets='<%= jspweblet.getURL( "org.dojotoolkit","dojo/resources/dojo.css") %>' id="blah2">
			This instance demos how to:
			<ol>
				<li>specify which plugins to load (see the plugins property): this instance loads EnterKeyHandling plugin, among others;</li>
				<li>specify options for a plugin (see the last item in the plugins array)</li>
			</ol>
		</textarea>
		<h3>..after</h3>
	</div>
	<a href="<%= jspweblet.getURL( "weblets.source","/jspdojotest.jsp") %>" target="_new">[Get the page source]</a>
 
   </body>
</html>
