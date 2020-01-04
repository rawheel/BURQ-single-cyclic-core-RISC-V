package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
import chisel3.core.SInt
class pc extends Module{
	val io = IO(new Bundle{

		val input = Input(UInt(32.W))
		val pc4 = Output(UInt(32.W))
		val pc_output = Output(UInt(32.W))


})

	val reg = RegNext(0.U(32.W))

	reg := io.input
	io.pc4 := reg+4.U
	io.pc_output := reg

}
		
