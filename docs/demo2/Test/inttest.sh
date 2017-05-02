#Try to compile the program
pass_compile=0
#Remove the previous binary file
javac test/integration_test.java
if [ "$?" -eq "0" ]; then
	pass_compile=1
else
	echo "Your program does not compile."
	exit
fi

#Test Account class
echo "Testing Account class"
declare result=$(timeout 3s java test/integration_test 0 123 abc@gmail.com rags2riches money)
if [ "$result" == $'123
abc@gmail.com
rags2riches
money' ]; then
	echo "passed"
else
	echo "failed"
fi

#Test Company class
echo "Testing Company class"
declare result=$(timeout 3s java test/integration_test 1 bankofa boa Costco 1 18)
if [ "$result" == $'bankofa
22.0
$22.00
10.0
1
11.0
boa

Costco
20.0
2
18.0' ]; then
	echo "passed"
else
	echo "failed"
fi

#Test Portfolio class
echo "Testing Portfolio class"
declare result=$(timeout 3s java test/integration_test 2 321 54.21 6 1.2 12)
if [ "$result" == $'321
1
6
54.21
$54.21
$39.81
$54.21
54.21' ]; then
	echo "passed"
else
	echo "failed"
fi
#Test Achievement class
echo "Testing Achievement class"
declare result=$(timeout 3s java test/integration_test 3 losses incomplete)
if [ "$result" == $'losses
incomplete' ]; then
	echo "passed"
else
	echo "failed"
fi
