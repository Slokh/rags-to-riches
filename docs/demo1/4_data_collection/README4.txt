The database and scripts to access the database were entirely developed and maintained by Kartik Patel. We used a server owned by him for previous projects.

The database was set up in MySQL. We access the database by running HttpClients in JAVA and sending requests to the PHP scripts on the server. This is way more secure than utilizing the JDBC connector and allows for accessing the data through asynchronous tasks. The HttpClient reads the text generated on the "webpage" by the PHP scripts and puts it into a JAVA string. We decided to send data as a simplified version of CSV that we made up utilizing commas and slashes. The JAVA methods parse these 'CSV' results and generate the objects needed.

The database and its tables can be found in the ../database folder and the scripts can be foudn in the ../scripts folder.

You can test out the scripts using the following examples in your browser:

- Registering an account (returns TRUE on success, returns FALSE on failure):
SUCCESS: http://parallel.gg/rags-to-riches/scripts/register-account.php?email=testaccount@gmail.com&username=testaccount&password=admin
FAILURE (email already in use): http://parallel.gg/rags-to-riches/scripts/register-account.php?email=rags@riches&username=rags&password=admin

- Logging into an account (returns account info on success, returns FALSE on failure):
SUCCES: http://parallel.gg/rags-to-riches/scripts/login-account.php?email=rags@riches&password=admin
FAILURE (wrong password): http://parallel.gg/rags-to-riches/scripts/login-account.php?email=rags@riches&password=wrongpassword

- Generating companies (returns company information):
http://parallel.gg/rags-to-riches/scripts/generate-companies.php?amount=10

- Grabbing a portfolio (returns portfolio information): 
http://parallel.gg/rags-to-riches/scripts/grab-portfolio.php?id=1&c0=PPL&c1=Dish+Network&c2=Verisign&c3=Church+%26+Dwight&c4=Robert+Half&c5=Western+Digital&c6=PepsiCo&c7=Mattel&c8=Newfield+Exploration&c9=McDonalds