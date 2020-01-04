package riscv

import chisel3._
import chisel3.util.Cat

class AluControl extends Module{
	val io = IO(new Bundle{
		val aluopp= Input(UInt(3.W))
		val Func3 = Input(UInt(3.W))
		val Func7 = Input(UInt(1.W))
		val AluC = Output(UInt(5.W))
	})

	
	when(io.aluopp === "b000".U){
		io.AluC := Cat(0.U, io.Func7, io.Func3)	// R
	}.elsewhen(io.aluopp === "b100".U){
		io.AluC := "b00000".U			// Load
	}.elsewhen(io.aluopp=== "b101".U){
		io.AluC := "b00000".U			// store
	}.elsewhen(io.aluopp === "b010".U){
		io.AluC := Cat("b10".U, io.Func3)	// SB
	}.elsewhen(io.aluopp === "b001".U && io.Func7==="b1".U){ // I
		when(io.Func3==="b101".U){
			io.AluC := Cat("b0".U, io.Func7, io.Func3)
		}.otherwise{
			io.AluC := Cat("b00".U, io.Func3)
		}
	}.elsewhen(io.aluopp === "b001".U && io.Func7==="b0".U){
		io.AluC := Cat("b00".U, io.Func3)
	}.elsewhen(io.aluopp === "b011".U){
		io.AluC := "b11111".U			// jal, jalr
	}.elsewhen(io.aluopp=== "b110".U){
		io.AluC := "b00000".U 			//lui
	}.otherwise{io.AluC:=DontCare}
	
}
