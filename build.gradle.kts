import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.21"
	kotlin("plugin.spring") version "1.7.21"
	kotlin("plugin.jpa") version "1.7.21"
	id("com.avast.gradle.docker-compose") version "0.16.11"
	id("org.flywaydb.flyway") version "7.15.0"

}

group = "com.example.anymind"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
	implementation("org.mybatis.generator:mybatis-generator-core:1.4.0")
	implementation("mysql:mysql-connector-java:8.0.28")
//	implementation("tk.mybatis:mapper:3.3.9")
	implementation(files("lib/uid-generator-1.0.1.jar"))
	implementation("mysql:mysql-connector-java:8.0.25")
	implementation("org.mybatis:mybatis:3.5.11")
	implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")
	implementation("org.apache.logging.log4j:log4j-api:2.17.0")
	implementation("org.apache.logging.log4j:log4j-core:2.17.0")
	implementation("org.apache.commons:commons-lang3:3.12.0")

//Thanks for using https://jar-download.com


	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


	// testing
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val TaskContainer.`composeUp`: TaskProvider<com.avast.gradle.dockercompose.tasks.ComposeUp>
	get() = named<com.avast.gradle.dockercompose.tasks.ComposeUp>("composeUp")


flyway {
    url = "jdbc:mysql://localhost/payments?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false"
    user = "root"
    password = "test"
}


tasks {
	named<BootRun>("bootRun") {
		jvmArgs = ArrayList(jvmArgs).apply {
			add("-Dspring.profiles.active=local")
		}
		dependsOn(flywayClean, flywayMigrate, composeUp)
	}
}

