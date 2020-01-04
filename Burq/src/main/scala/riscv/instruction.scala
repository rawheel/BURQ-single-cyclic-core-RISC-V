package riscv

import chisel3._
import chisel3.util.Cat
//import chisel3.util.Fill
import chisel3.util.experimental.loadMemoryFromFile


class instruction extends Module{
	val io = IO(new Bundle{

		val write_address = Input(UInt(10.W)) //instruction table input
		val r_data = Output(UInt(32.W))  //output from instruction mem
		
		


})
		val mem =Mem(1024,UInt(32.W)) //1032 lines of instruction file and 32.W

		io.r_data := mem(io.write_address)
		loadMemoryFromFile(mem,"/home/raheel/aa.txt")
	
}
		
