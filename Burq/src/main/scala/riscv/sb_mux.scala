package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
import chisel3.core.SInt


class sb_mux extends Module{
	val io = IO(new Bundle{


	val sb_i = Input(SInt(32.W))

	val alu_branch = Input(UInt(1.W))

	val branch_control =Input(UInt(1.W)) 
	val and_out = Output(UInt(1.W)) 
	val mul = Output(UInt(1.W))
})


	val alu_m = Module(new alu)
	val immediate_m = Module(new Immediate)
	val control_m = Module(new Control)


	io.sb_i := immediate_m.io.Sb_Type
	io.alu_branch := alu_m.io.alu_brancho
	io.branch_control := control_m.io.Branch
        
	 io.mul := io.branch_control & io.alu_branch 

	when( io.mul === "b1".U){

			io.and_out:= "b1".U
	}.otherwise{
		io.and_out:="b0".U
}
	


}
