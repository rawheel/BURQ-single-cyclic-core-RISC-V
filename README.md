# RISC-V Single Cycle Core Burq
### Designed by Raheel Siddiqui

Hello,
	I am a student of Software Engineering in Usman Institute of Technology. I have totally understood riscv Datapath and implemented it on logisim,now I can say I have much better command on that.
Now, I've also developed single cyclic risc-v core it is a great acheivement for me looking forward to pipeline process.
It's such a good experience to work on something which has a huge impact and I always wanted to work on something creative and efficient and I am giving my best for that.



First of all get started by cloning this repository on your machine.  
```ruby
https://github.com/merledu/Burq-Core.git 
```
Create a .txt file and place the ***hexadecimal*** code of your instructions simulated on ***Venus*** (RISC-V Simulator)\
Each instruction's hexadecimal code must be on seperate line as following. This program consists of 9 instructions.
00400093
00500193
018004EF
00402223
00402283
00521A63
00120333
00C0006F
40308233
00048067
```
Then perform the following step
```ruby
cd Burq/src/main/scala/riscv
```
Open **instruction.scala** with this command. You can also manually go into the above path and open the file in your favorite text editor.
```ruby
open instruction.scala
```
Find the following line
``` python
loadMemoryFromFile(mem, "/home/raheel/aaa.txt")
```
Change the .txt file path to match your file that you created above storing your own program instructions.\
After setting up the InstructionMem.scala file, go inside the Burq folder.
```ruby
cd Burq
```
And enter
```ruby
sbt
```
When the terminal changes to this type
```ruby
sbt:Burq>
```
Enter this command
```ruby
sbt:Burq> test:runMain riscv.Launcher conn
```
After you get success
```ruby
sbt:Burq> test:runMain riscv.Launcher conn --backend-name verilator
```
After success you will get a folder ***test_run_dir*** on root of your folder. Go into the examples folder inside.\
There you will find the folder named Top. Enter in it and you can find the conn.vcd file which you visualise on **gtkwave** to\
see your program running.

