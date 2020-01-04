package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
import chisel3.core.SInt
class alu extends Module{
	val io = IO(new Bundle{
		val A = Input(SInt(32.W))
		val B = Input(SInt(32.W))
		val alu_c = Input(UInt(5.W))
		val alu_output = Output(SInt(32.W))
		val alu_brancho = Output(UInt(1.W))

		
})
	io.alu_brancho := "b0".U

		when(io.alu_c === "b00000".U){
			io.alu_output := io.A+io.B //0,add
	
		}.elsewhen(io.alu_c === "b00001".U){ //1,sll
			val shift_b = io.B(4,0) 
			io.alu_output := io.A << shift_b
		
		}.elsewhen(io.alu_c === "b00010".U){ //2,slt
			when(io.A<io.B){
				io.alu_output:= 1.S
			}.otherwise{
				io.alu_output:= 0.S
			}
			
		}.elsewhen(io.alu_c === "b00100".U){
			io.alu_output:= io.A^io.B//4,xor

		}.elsewhen(io.alu_c==="b00101".U){
			val rshift_b = io.B(4,0)
			io.alu_output:= io.A >> rshift_b//5,srl

	
		}.elsewhen(io.alu_c==="b00110".U){//6,or
			io.alu_output:=io.A|io.B
		
		}.elsewhen(io.alu_c==="b00111".U){
			io.alu_output:=io.A&io.B  //7,and
	
		}.elsewhen(io.alu_c==="b01000".U){
			io.alu_output:= io.A-io.B //8,sub
	
		}.elsewhen(io.alu_c==="b01101".U){ 
			val rshift_br1 = io.B(4,0)
	
			io.alu_output:= io.A >> rshift_br1  //13,sra
	
		}.elsewhen(io.alu_c === "b10000".U){ //16,beq
			when(io.A===io.B){
				io.alu_output:=1.S
			}.otherwise{
				io.alu_output:= 0.S
		}
			
	
		}.elsewhen(io.alu_c === "b10001".U || io.alu_c === "b10111".U){ //17,bne
			when(io.A!=io.B){
				io.alu_output:=1.S
			}.otherwise{
				io.alu_output:=0.S
			}
		
		}.elsewhen(io.alu_c === "b10100".U){ //20,blt
			when(io.A<io.B){
				io.alu_output:=1.S
			}.otherwise{
				io.alu_output:= 0.S
			}
	
		}.elsewhen(io.alu_c === "b10101".U){ //21,bge
			when(io.A>=io.B){
				io.alu_output:=1.S
			}.otherwise{
				io.alu_output:= 0.S
			}
	
	}.elsewhen(io.alu_c==="b11111".U){
				io.alu_output:= io.A
	

	}.elsewhen(io.alu_c === "b00011".U || io.alu_c === "b10110".U){ //3,sltu/22,bltu
	
		when(io.A.asUInt<io.B.asUInt){
			io.alu_output:=1.S
		}.otherwise{
			io.alu_output:= 0.S		
		}
	

	}.otherwise{
		io.alu_output:=DontCare
	}


	when(io.alu_c(4,3) === "b10".U && io.alu_output.asUInt==="h1".U){
		io.alu_brancho:= "b1".U

	}//.otherwise{
		//io.alu_brancho:=0.U
	//} 		



}
