<%-- One of the most powerful things you can do with tag files is establish a system of HTML templates for your application to use. 
    This templating system can take care of many of the repetitive tasks needed on pages across your application, cutting down on 
    duplicated code and making it easier to change the design of your site. 
    
    tag files work essentially like JSP files do. They contain the same syntax and must follow the same basic rules, and at run time
	they get translated and compiled into Java just like JSPs do. Tag files can use any normal template text (including HTML), any 
	other JSP tag, declarations, scriptlets, expressions, and expression language. It should not be surprising, however, that there
	are some minor differences between the two file formats, mainly concerning the directives available for tag files.
	
	Tag files can also use the include and taglib directives to include files and other tag libraries in the JSP, but there is no 
	page directive in tag files. The include directive can be used to include .jsp, .jspf, and other .tag files in a .tag file, or 
	.jspx and other .tagx files in a .tagx file. Using a taglib directive in a tag file is identical to using one in a JSP file.

	Instead of the page directive, tag files have a tag directive. This directive replaces the necessary functionality from JSPÃÂ¢ÃÂÃÂs 
	page directive and also replaces many of the configuration elements from a <tag> element in a TLD file. The tag directive has the
	following attributes, none of which are required:

		* pageEncoding : This is equivalent to the page directive pageEncoding attribute and sets the character encoding of the tagÃÂ¢ÃÂÃÂs
		                 output.
        * isELIgnored : Equivalent to its counterpart in the page directive, this instructs the container not to evaluate EL 
                        expressions in the tag file and defaults to false.

		* language : This specifies the scripting language used in the tag file (currently only Java is supported), just like the 
		             language attribute in the page directive.

		* deferredSyntaxAllowedAsLiteral : Just like the page directive attribute, this tells the container to ignore and not parse 
		                                   deferred EL syntax within the tag file.

		* trimDirectiveWhitespaces : This tells the container to trim white space around directives, equivalent to the same attribute
							 		on the page directive.

		* import : This attribute works just like the page directiveÃÂ¢ÃÂÃÂs import attribute. You can specify one or more comma-separated 
		           Java classes to import in this attribute, and you can use the attribute multiple times in the same tag directive 
		           or across multiple tag directives.

		* description : This is the equivalent of the <description> element in a TLD file, and specifying it can be helpful for 
		                developers to understand your tag better.

		* display-name : Equivalent to the <display-name> element in a TLD, there is usually no need to specify this.
		* small-icon and large-icon : These attributes essentially replace the <icon> element in a TLD, and you should never need to
									 specify them.

		* body-content : This is the replacement for <body-content> in a TLD, with one minor change: Its valid values are empty, 
						scriptless, and tagdependent. The JSP value available in a TLD is not valid for the body content of a tag 
						specified in a tag file. Due to the limitations of how tag files work, you cannot use scriptlets or 
						expressions within the nested body content when using a tag that was defined in a tag file. scriptless is the
						default value for this attribute.

		* dynamic-attributes : This string attribute is the counterpart of the <dynamic-attributes> element in a TLD and indicates 
								whether dynamic attributes are enabled. By default the value is blank, which means that dynamic 
								attributes are not supported. To enable dynamic attributes, set its value to the name of the EL 
								variable you want created to hold all of the dynamic attributes. The EL variable will have a type of 
								Map<String, String>. The map keys will be dynamic attribute names, and the values attribute values.
								
	Notice that equivalent directive attributes are missing for the <name>, <tag-class>, <tei-class>, <variable>, <attribute>, and 
	<tag-extension> elements. The tag name is inferred from and always equal to the tag filename (minus the .tag extension), and 
	<tag-class> is not needed because the tag file is the tag handler (or will be, after the container compiles it). There is no way
	to specify a TagExtraInfo class or a tag extension for tag files because there is simply no equivalent. This leaves <variable> 
	and <attribute>, which are replaced with the variable and attribute directives, respectively.

    The first directive in this file establishes that uses of the tag can contain body content, and that directive white space should
    be trimmed. Note the use of the trimDirectiveWhitespace attribute to accomplish this; the <jsp-config> in the deployment 
    descriptor does not affect tag files, so you need to do this manually in each tag file. 
--%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true" required="true" %>
<%@ attribute name="extraHeadContent" fragment="true" required="false" %>
<%@ attribute name="extraNavigationContent" fragment="true" required="false" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<%-- When you use a JSP tag, you typically specify all the attributes as normal XML attributes and then the contents within the tag 
    make up the entire tag body. However, when an attribute value is too long or contains JSP content, you can use the 
    <jsp:attribute> tag within the tag body to specify the attribute value. 
    
    At this point the attribute is specified within the tag body content, so to specify the real tag body content you need to use the
    <jsp:body> tag. Everything in this tag becomes the tag body for the enclosing tag (<template:main> in this case).
    
    This tag specifies several links that go within the sidebar of the page on every page that uses this template tag. It also 
    provides extraHeadContent and extraNavigationContent attributes to allow consuming pages to add extra content to the head tag or
    sidebar.
    
    Although tag files cannot technically extend one another, this is essentially what you are doing. The main tag sets up a 
    foundation for the template and the loggedOut and basic tags build upon that foundation. Now you can see the full power of tag 
    files.
--%>
<template:main htmlTitle="${htmlTitle}" bodyTitle="${bodyTitle}">
    <jsp:attribute name="headContent">
        <jsp:invoke fragment="extraHeadContent" />
    </jsp:attribute>
   
    <jsp:attribute name="navigationContent">
   
        <%-- The tag you’ll use most often is <security:authorize>. Its nested contents are evaluated only if the authorization rule
            defined with its attributes evaluates to true. If you specify the access attribute, it evaluates the value as a security
            expression (so you can use hasAuthority and the other functions). The url attribute allows you to specify an 
            application-relative URL, and Spring Security finds and evaluates the matching URL security rule, if any. If you use 
            url, you can also specify method to restrict the permitted HTTP methods. You cannot specify access and url at the same 
            time. basic.tag demonstrates using <security:authorize> to hide links that the user isn’t authorized to access. 
        --%>
        <security:authorize access="hasAuthority('ROLE_INSTRUCTOR')">
        	 <c:out value="ROLE_INSTRUCTOR"/>
        	 <br />
        </security:authorize>
        
        <security:authorize access="hasAuthority('ROLE_LEARNER')">
            <c:out value="ROLE_LEARNER"/>
            <br />
        </security:authorize>
        
        <security:authorize access="hasAuthority('ROLE_LMSADMINISTRATOR')">
        	 <c:out value="ROLE_LMSADMINISTRATOR"/>
        	 <br />
        </security:authorize>
        
        <security:authorize access="hasAuthority('ROLE_REGULATORYANALYST')">
            <c:out value="ROLE_REGULATORYANALYST"/>
            <br />
        </security:authorize>
        
        <security:authorize access="hasAuthority('ROLE_TRAININGADMINISTRATOR')">
            <c:out value="ROLE_TRAININGADMINISTRATOR"/>
            <br />
        </security:authorize>
        
        
        <%-- While youâre securing forms, you should know that the "best practice" today is to disable autocomplete for login forms. 
			This protects users who share computers with other people, such as in a public library. Making this change is as simple 
			as adding autocomplete="off" to the <form:form>, <form:input>, and <form:password> tags in login.jsp. With CSRF 
			protection enabled, Spring Security requires the logout to happen over POST. It does not respond to logout requests over 
			GET. This is why the logout link in the Customer Support application now submits an invisible form. 
        --%>
        <a href="javascript:void 0;" onclick="postInvisibleForm('<c:url value="/logout" />', { });"><spring:message code="nav.item.logout" /></a><br />
        <jsp:invoke fragment="extraNavigationContent" />
        
        <%-- The <security:authentication> tag is very useful. It allows you to output properties of the current Authentication. The
            following line demonstrates this by printing out the username for the current principal. 
        --%>
        <br />Welcome, <security:authentication property="principal.username" />!
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody />
    </jsp:body>
</template:main>
