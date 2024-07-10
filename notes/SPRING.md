### What IS Spring?

At its core, Spring offers a *container*, often
referred to as the Spring *application context*,
that creates and manages application components.
These components, or beans,
are wired together inside the Spring application
context to make a complete application, much like
bricks, mortar, timber, nails, plumbing, and
wiring are bound together
to make a house


The act of wiring beans together is based on a pattern known as dependency injection
(DI). Rather than have components create and maintain the life cycle of other beans
that they depend on, a dependency-injected application relies on a separate entity
(the container) to create and maintain all components and inject those into the beans
that need them. This is done typically through constructor arguments or property
accessor methods.

Automatic configuration has its roots in the Spring techniques known as **autowiring**
and **component scanning**. With component scanning, Spring can automatically discover
components from an application’s classpath and create them as beans in the Spring
application context. With autowiring, Spring automatically injects the components
with the other beans that they depend on.

### Spring MVC
Spring comes with a powerful web framework known as Spring MVC. At the center of
Spring MVC is the concept of a controller, a class that handles requests and responds
with information of some sort. In the case of a browser-facing application, a controller
responds by optionally populating model data and passing the request on to a view to
produce HTML that’s returned to the browser.
