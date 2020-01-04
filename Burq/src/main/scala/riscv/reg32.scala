package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill

class reg32 extends Module{
	val io = IO(new Bundle{
		val imm_rs1 = Input(UInt(5.W))
		val imm_rs2 = Input(UInt(5.W))
		val rd_reg = Input(UInt(5.W))
		val write_data = Input(SInt(32.W))
		val reg_write = Input(UInt(1.W))


		val rs1_output= Output(SInt(32.W))
		val rs2_output= Output(SInt(32.W))
		
})

	val reg = Reg(Vec(32,SInt(32.W)))
	reg(0) := 0.S
	
	io.rs1_output := reg(io.imm_rs1)
	io.rs2_output := reg(io.imm_rs2)


	when (io.reg_write ===1.U){
		when(io.rd_reg==="b00000".U){
			reg(io.rd_reg):=0.S
		}.otherwise{
			reg(io.rd_reg) := io.write_data
		}
		
		
	}
		

			

	 



}
