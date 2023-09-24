# Read Me First
<h2>Tech Stack In Project</h2>
- Java version 17
<br/>
- Spring Boot 3.1.4
<br/>
- Maven 3.8.7
<br/>
- Postgres SQL
<br/>
- Swagger-UI
<br/>
- Spring Data Jpa
<br/>
- Hibernate

<h2>List Service</h2>
- http://localhost:8081/v1/gpay/getSessionKey?custId=?&trxType=?
<br/>
- http://localhost:8081/v1/gpay/refund
<br/>
- http://localhost:8081/v1/gpay/transfer
<br/>
- http://localhost:8081/v1/gpay/topup
<br/>

<h2>Intallation And Run</h2>
- Pastikan Config Database di application.properties sesuai , di dalam project ini menggunakan user database "postgres"
<br/>
- alter script_init.sql. Note : schema database disesuaikan setiingan lokal
<br/>
- terdapat customer & wallet default yaitu custId:1 & custId:2 script insert ada di script_init.sql
<br/>
- port yang di gunakan 8081 
<br/>
- Root endpoint http://localhost:8081/
<br/>
- build projcet : mvn clean install
<br/>
- run project : mvn spring-boot:run
<br/>
- link documentation swagger : http://localhost:8081/swagger-ui/index.html#
<br/>

<h2>Use Service</h2>
- Setiap service transaction menggunakan "keyId" yang di dapat di service /v1/gpay/getSessionKey?custId=?&trxType=?
<br/>
- keyId sebagai tanda claim transaksi di setiap session, setiap transaksi mempunyai trxType berbeda
<br/>

<h2>Database Design</h2>
<br/>

<img src="src/main/resources/docs/architecture_db.png" width="1060"/>

<br/>

<h2>Swagger</h2>
<br/>

<img src="src/main/resources/docs/img.png" width="1060"/>

<br/>
