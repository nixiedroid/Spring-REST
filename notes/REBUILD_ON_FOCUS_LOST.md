https://intellij-support.jetbrains.com/hc/en-us/community/posts/360009588299--Solved-IntelliJ-keeps-build-files-when-focus-out?page=1#community_comment_360002168019
Please check "On frame deactivation" option in Run
configuration of the
project: https://i.imgur.com/5TVJZNd.png

And these two options could also be related:

1. Settings (Preferences on macOS) | Build,
   Execution, Deployment | Compiler | Rebuild
   module on dependency change

2. Settings (Preferences on macOS) | Build,
   Execution, Deployment | Compiler | Build
   project automatically/