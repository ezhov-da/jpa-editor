plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#jpa-support
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.10"
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("org.swinglabs:swingx:1.6.1")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("com.h2database:h2:1.4.196")
    implementation("org.hibernate:hibernate-core:5.2.12.Final")
    implementation("org.codehaus.groovy:groovy-all:2.4.12")
    implementation("javax:javaee-api:7.0")
    implementation("junit:junit:4.13")
    implementation("com.fifesoft:rsyntaxtextarea:2.6.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
}

application {
    mainClass.set("ru.ezhov.jpa.editor.AppKt")
}
