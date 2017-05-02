#Try to compile the program
pass_compile=0
#Remove the previous binary file
javac test/unit_test.java
if [ "$?" -eq "0" ]; then
	pass_compile=1
else
	echo "Your program does not compile."
	exit
fi

echo "Testing Account getId"
declare result=$(timeout 3s java test/unit_test 0 24)
if [ "$result" == $'24' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Account getEmail"
declare result=$(timeout 3s java test/unit_test 1 www@g.com)
if [ "$result" == $'www@g.com' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Account getUser"
declare result=$(timeout 3s java test/unit_test 2 jane)
if [ "$result" == $'jane' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Account getPass"
declare result=$(timeout 3s java test/unit_test 3 pswd123)
if [ "$result" == $'pswd123' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getName"
declare result=$(timeout 3s java test/unit_test 4 wmart)
if [ "$result" == $'wmart' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getPrice"
declare result=$(timeout 3s java test/unit_test 5 55.44)
if [ "$result" == $'55.44' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getPriceText"
declare result=$(timeout 3s java test/unit_test 6 55.44)
if [ "$result" == $'$55.44' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getPriceHistory"
declare result=$(timeout 3s java test/unit_test 7 55.44)
if [ "$result" == $'55.44' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getWeek"
declare result=$(timeout 3s java test/unit_test 8 3)
if [ "$result" == $'3' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getPriceAt"
declare result=$(timeout 3s java test/unit_test 9 1)
if [ "$result" == $'22.0' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getTicker"
declare result=$(timeout 3s java test/unit_test 10 wmt)
if [ "$result" == $'wmt' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getDescription"
declare result=$(timeout 3s java test/unit_test 11)
if [ "$result" == $'' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company getRealName"
declare result=$(timeout 3s java test/unit_test 12 IBM)
if [ "$result" == $'IBM' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company goToNextWeek"
declare result=$(timeout 3s java test/unit_test 14)
if [ "$result" == $'0
11.0
1
22.0' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Company setPrice"
declare result=$(timeout 3s java test/unit_test 15 24.0)
if [ "$result" == $'11.0
24.0' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio getId"
declare result=$(timeout 3s java test/unit_test 16 564)
if [ "$result" == $'564' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio getAmountofStock"
declare result=$(timeout 3s java test/unit_test 17 14)
if [ "$result" == $'14' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio updateStock"
declare result=$(timeout 3s java test/unit_test 19 14)
if [ "$result" == $'0
14' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio getBalance"
declare result=$(timeout 3s java test/unit_test 20)
if [ "$result" == $'3.0' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio getBalanceAt"
declare result=$(timeout 3s java test/unit_test 21)
if [ "$result" == $'3.0' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio getBalanceText"
declare result=$(timeout 3s java test/unit_test 22)
if [ "$result" == $'$3.00' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio updateBalance(buy)"
declare result=$(timeout 3s java test/unit_test 23 10.10 11)
if [ "$result" == $'0.0
-111.1' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Portfolio updateBalance(sell)"
declare result=$(timeout 3s java test/unit_test 24 1.1 21)
if [ "$result" == $'0.0
23.1' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Achievement Class"
echo "Testing Achievement getName"
declare result=$(timeout 3s java test/unit_test 25)
if [ "$result" == $'winstreak' ]; then
	echo "passed"
else
	echo "failed"
fi

echo "Testing Achievement getProgress"
declare result=$(timeout 3s java test/unit_test 26)
if [ "$result" == $'fin' ]; then
	echo "passed"
else
	echo "failed"
fi
