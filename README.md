# DBC-Android
Fluent design by contract assertions for Android

## Installation
Add the JitPack repository to your root build.gradle:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

In your module's build.gradle file, add the dependency:
```groovy
dependencies {
    ...
    compile 'com.github.busybusy:DBC-Android:{LatestVersionHere}'
}
```

## Usage
In your Application's onCreate() method, initialize the configuration on which categories should throw exception

```java
DbcManager.getInstance().setConfig(BuildConfig.DBC_REQUIRE, BuildConfig.DBC_CHECK, BuildConfig.DBC_ENSURE);
```

It is recommended that you utilize the android build systems configuration / flavors to configure DbcManager
This is accomplished by adding the following lines in your module's build.gradle file under the appropriate sections of your build config
 
```groovy
android {
    defaultConfig {
        ...
        buildConfigField "boolean", "DBC_REQUIRE", "true"        
        buildConfigField "boolean", "DBC_CHECK", "true"        
        buildConfigField "boolean", "DBC_ENSURE", "true"
    }
    
    buildTypes {
        release {
            ...
            buildConfigField "boolean", "DBC_CHECK", "false"        
            buildConfigField "boolean", "DBC_ENSURE", "false"
        }
        debug {
            ...
        }
    }
}
```

Use the design by contract assertions throughout your code to verify assumptions

```java
import static com.busybusy.dbc.Dbc.*;

@NonNull
public String someMethod(@NonNull String arg1) {
    requireThat(arg1).isNotNull();
    
    String postFix = getPostFix();
    checkThat(postFix).passes(CustomChecks::someCheck); //Java 8 method refference
    
    String result = arg1 + postFix;
    return ensureThat(result).isValid();
}
```

## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.