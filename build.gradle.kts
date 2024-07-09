import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.jetbrains.kotlin.kapt") version "1.7.10"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "itmo_test_task"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}
repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

	runtimeOnly("org.postgresql:postgresql")
}

kapt {
	correctErrorTypes = true
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = freeCompilerArgs + "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

