##freemarker属性配置

* spring.freemarker.allow-request-override=false # Set whether HttpServletRequest attributes are allowed to override (hide) controller generated model attributes of the same name.
* spring.freemarker.allow-session-override=false # Set whether HttpSession attributes are allowed to override (hide) controller generated model attributes of the same name.
* spring.freemarker.cache=false # Enable template caching.
* spring.freemarker.charset=UTF-8 # Template encoding.
* spring.freemarker.check-template-location=true # Check that the templates location exists.
* spring.freemarker.content-type=text/html # Content-Type value.
* spring.freemarker.enabled=true # Enable MVC view resolution for this technology.
* spring.freemarker.expose-request-attributes=false # Set whether all request attributes should be added to the model prior to merging with the template.
* spring.freemarker.expose-session-attributes=false # Set whether all HttpSession attributes should be added to the model prior to merging with the template.
* spring.freemarker.expose-spring-macro-helpers=true # Set whether to expose a RequestContext for use by Spring's macro library, under the name "springMacroRequestContext".
* spring.freemarker.prefer-file-system-access=true # Prefer file system access for template loading. File system access enables hot detection of template changes.
* spring.freemarker.prefix= # Prefix that gets prepended to view names when building a URL.
* spring.freemarker.request-context-attribute= # Name of the RequestContext attribute for all views.
* spring.freemarker.settings.*= # Well-known FreeMarker keys which will be passed to FreeMarker's Configuration.
* spring.freemarker.suffix= # Suffix that gets appended to view names when building a URL.
* spring.freemarker.template-loader-path=classpath:/templates/ # Comma-separated list of template paths.
* spring.freemarker.view-names= # White list of view names that can be resolved.