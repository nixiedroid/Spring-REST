### Sample Spring project using REST API

Currently running on https://spring.nixiedroid.com

### SiteMap 

```mermaid
---
title: Sitemap
---

flowchart TB
root{"/(root)"} --> cof(/coffees)
root --> act(/actuator)
root --> inf(/info)

cof --> GET
cof --> POST
cof --> PUT

```
    
### Notes
see [MAP](notes/MAP.md)