package riscv

import chisel3._

class ITD extends Module {
	val  io = IO(new Bundle {
		val opCode = Input(UInt(7.W))
		val RType = Output(UInt(1.W))
		val Load = Output(UInt(1.W))
		val Store = Output(UInt(1.W))
		val Branch = Output(UInt(1.W))
		val IType = Output(UInt(1.W))
		val Jalr = Output(UInt(1.W))
		val Jal = Output(UInt(1.W))
		val Lui = Output(UInt(1.W))
	})
	
	io.RType := 0.U
	io.Load := 0.U
	io.Store := 0.U
	io.Branch := 0.U
	io.IType := 0.U
	io.Jalr := 0.U
	io.Jal := 0.U
	io.Lui := 0.U

	when(io.opCode === "b0110011".U){
		io.RType := 1.U	
	}.elsewhen(io.opCode === "b0000011".U){
		io.Load := 1.U
	}.elsewhen(io.opCode === "b0100011".U){
		io.Store := 1.U
	}.elsewhen(io.opCode === "b1100011".U){
		io.Branch := 1.U
	}.elsewhen(io.opCode === "b0010011".U){
		io.IType := 1.U
	}.elsewhen(io.opCode === "b1100111".U){
		io.Jalr := 1.U
	}.elsewhen(io.opCode === "b1101111".U){
		io.Jal := 1.U
	}.elsewhen(io.opCode === "b0110111".U){
		io.Lui := 1.U
	}
		
}
