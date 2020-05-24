# Todo Annotation
With the help of this you can annotate a class, method or a field which needs to be looked into in the future.
Compiler throws error during compilation when there are some Todos in the project that needs to be resolved

## Getting Started
Using Gradle
```
apply plugin: 'kotlin-kapt'

implementation 'com.innovatorlabs.annotations:annotations:1.0.0'
kapt 'com.innovatorlabs.annotationcompiler:annotationcompiler:1.0.0'
```
