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