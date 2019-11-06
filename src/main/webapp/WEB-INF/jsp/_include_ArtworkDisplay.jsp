<%@ page contentType = "text/html; charset = UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<s:set var="ArtFileLink" value="CurrentArtworkLink" />
<s:url value="%{ArtFileLink}" var="URIResource"/>
<p>ArtFileLink is <s:property value="CurrentArtworkLink"/></p>
<img src='<s:property value="#URIResource"/>' alt='<s:property value="CurrentArtworkALT"/>' id='<s:property value="CurrentArtworkID"/>' />
