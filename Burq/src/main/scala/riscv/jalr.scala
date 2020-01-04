package riscv
import chisel3.core.SInt
import chisel3._
import chisel3.util.Cat
//import chisel3.util.Fill
import chisel3.util.experimental.loadMemoryFromFile


class jalr extends Module{
	val io = IO(new Bundle{

	val j_rs1 = Input(SInt(32.W))

	val j_imm = Input(SInt(32.W))

	val jalr_output = Output(SInt(32.W))
	


		

})

	io.jalr_output:=(io.j_rs1+io.j_imm) & 4294967294L.S


}

//jalr
	
//	when(controlc.io.ExtendSel==="b00".U){

//	io.output_imm_mux := immediatec.io.I_Type
//	io.jalr_sum := io.output_imm_mux.asUInt +  reg32c.io.rs1_output.asUInt

//	io.jalr_output := io.jalr_sum & "hfffffffe".U
//         io.jalr_output := io.jalr_sum & 4294967294L.U
	
//	}.elsewhen(controlc.io.ExtendSel==="b01".U){

//	io.output_imm_mux:=immediatec.io.S_Type

//	}.elsewhen(controlc.io.ExtendSel==="b10".U){

//	io.output_imm_mux:=immediatec.io.U_Type
//	}.otherwise{io.output_imm_mux:= DontCare
	
//	}
