<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>Nov2022POMSeries</groupId>
	<artifactId>Nov2022POMSeries</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<extentreports-version>5.0.8</extentreports-version>
		<aspectj.version>1.9.5</aspectj.version>
	</properties>


	<dependencies>
		<dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>4.8.1</version>
		</dependency>

		<dependency>
			<groupId>org.testng</groupId>
			<artifactId>testng</artifactId>
			<version>6.14.3</version>
		</dependency>

		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>3.9</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>3.9</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml-schemas</artifactId>
			<version>3.9</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-scratchpad</artifactId>
			<version>3.9</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>ooxml-schemas</artifactId>
			<version>1.1</version>
		</dependency>

		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>openxml4j</artifactId>
			<version>1.0-beta</version>
		</dependency>

		<dependency>
			<groupId>com.aventstack</groupId>
			<artifactId>extentreports</artifactId>
			<version>${extentreports-version}</version>
		</dependency>
		
		<dependency>
			<groupId>io.qameta.allure</groupId>
			<artifactId>allure-testng</artifactId>
			<version>2.12.0</version>
		</dependency>

		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>${aspectj.version}</version>
		</dependency>
		
		

	</dependencies>
	
	
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>


			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.20</version>

				<configuration>
					<forkCount>3</forkCount>
					<reuseForks>true</reuseForks>
					<argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
					<suiteXmlFiles>
						<!-- <suiteXmlFile>src/test/resources/testrunners/testng_regression.xml</suiteXmlFile> -->
						<suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
					</suiteXmlFiles>

					<argLine>
						-javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
					</argLine>

				</configuration>

				<dependencies>
					<dependency>
						<groupId>org.aspectj</groupId>
						<artifactId>aspectjweaver</artifactId>
						<version>${aspectj.version}</version>
					</dependency>
				</dependencies>

			</plugin>

			


		</plugins>
	</build>




</project>