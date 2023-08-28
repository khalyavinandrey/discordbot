plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenLocal()
	mavenCentral()
	maven("https://m2.dv8tion.net/releases")
	maven("https://m2.chew.pro/releases")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.liquibase:liquibase-core:4.23.1")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.2")
	implementation(platform("org.testcontainers:testcontainers-bom:1.17.6"))
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.mockito:mockito-core:5.5.0")
	testImplementation("org.testcontainers:postgresql")

	implementation("net.dv8tion:JDA:4.4.0_352")
	implementation("pw.chew:jda-chewtils:1.24.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	archiveFileName.set("bot.jar")
}