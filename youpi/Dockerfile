# Step 1: Base image chunein jismein Java (JDK 21) pehle se ho
FROM openjdk:21-jdk-slim

# Step 2: Ek directory banayein jahan app ka code rakha jayega
WORKDIR /app

# Step 3: Maven Wrapper files ko copy karein
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# ✅✅✅ YEH NAYI LINE HAI ✅✅✅
# Step 4: mvnw ko run karne ki permission dein
RUN chmod +x ./mvnw

# Step 5: Dependencies download karein (taaki build fast ho)
RUN ./mvnw dependency:go-offline

# Step 6: Poore source code ko copy karein
COPY src ./src

# Step 7: Application ko package karein (JAR file banayein)
RUN ./mvnw clean package -DskipTests

# Step 8: Batayein ki app kaun se port par chalegi
EXPOSE 8080

# Step 9: Application ko run karne ki final command
CMD ["java", "-jar", "target/youpi-0.0.1-SNAPSHOT.jar"]