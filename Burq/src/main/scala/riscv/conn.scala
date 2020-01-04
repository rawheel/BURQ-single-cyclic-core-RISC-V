package riscv

import chisel3._
import chisel3.util.Cat
import chisel3.util.Fill
import chisel3.core.SInt
class conn extends Module{
	val io = IO(new Bundle{

		
		val output = Output(SInt(32.W))
		

})     
	//modules

	val controlc = Module(new Control)
	val immediatec = Module(new Immediate)
	val reg32c = Module(new reg32)
	val aluc = Module(new alu)
	val aluconc=Module(new AluControl)
	val instruction_c= Module(new instruction)
	val pc_main = Module(new pc)
	val jalr_c = Module(new jalr) //n
	val mem_c = Module(new main_mem)	


	pc_main.io.input := pc_main.io.pc4
	immediatec.io.pc := pc_main.io.pc_output

	instruction_c.io.write_address := pc_main.io.pc_output(11,2)
	controlc.io.OpCode :=instruction_c.io.r_data(6,0)
	
	//instruction table
	
	reg32c.io.imm_rs1 := instruction_c.io.r_data(19,15)
	reg32c.io.imm_rs2 := instruction_c.io.r_data(24,20)
	reg32c.io.rd_reg:= instruction_c.io.r_data(11,7)
	reg32c.io.reg_write:=controlc.io.RegWrite
	immediatec.io.instruction := instruction_c.io.r_data
	
	//immediate
	
	aluconc.io.Func3:= instruction_c.io.r_data(14,12)
	aluconc.io.Func7:= instruction_c.io.r_data(30)
	
	//alu
	
	aluconc.io.aluopp := controlc.io.AluOp
	
	aluc.io.alu_c := aluconc.io.AluC

	when(controlc.io.Operand_aSel==="b00".U){

		aluc.io.A := reg32c.io.rs1_output

	}.elsewhen(controlc.io.Operand_aSel==="b10".U){

		aluc.io.A := pc_main.io.pc4.asSInt

	}/**.elsewhen(controlc.io.Operand_aSel==="b01".U){

		aluc.io.A := pc_main.io.pc_output.asSInt

	}**/.elsewhen(controlc.io.Operand_aSel==="b11".U){

		aluc.io.A := reg32c.io.rs1_output

	}.otherwise{

		aluc.io.A:=DontCare

	}

	when(controlc.io.ExtendSel ==="b00".U && controlc.io.Operand_bSel==="b1".U){
		
		aluc.io.B:=immediatec.io.I_Type
	}.elsewhen(controlc.io.ExtendSel==="b10".U && controlc.io.Operand_bSel==="b1".U){
		
		aluc.io.B :=immediatec.io.S_Type 
	}.elsewhen(controlc.io.ExtendSel==="b01".U && controlc.io.Operand_bSel==="b1".U){
		
		aluc.io.B :=immediatec.io.U_Type 
	}.otherwise{
			
		aluc.io.B:=reg32c.io.rs2_output

	}

	reg32c.io.write_data := aluc.io.alu_output //yahan hun
	


	//jalr 

	when(controlc.io.ExtendSel==="b00".U){

                jalr_c.io.j_imm := immediatec.io.I_Type
		

	}.otherwise{

		jalr_c.io.j_imm:= DontCare
	
	}
	

	jalr_c.io.j_rs1 := reg32c.io.rs1_output.asSInt
	//io.jalr_out  := jalr_c.io.jalr_output	



	//muxx sel uj and jalr
	

	when (controlc.io.NextPcSel==="b10".U){

		pc_main.io.input := immediatec.io.Uj_Type.asUInt

	}.elsewhen(controlc.io.NextPcSel==="b11".U){
			
		pc_main.io.input := jalr_c.io.jalr_output.asUInt

	}.elsewhen((aluc.io.alu_brancho & controlc.io.Branch)==="b1".U && controlc.io.NextPcSel==="b01".U){
			
		pc_main.io.input := immediatec.io.Sb_Type.asUInt

	}.elsewhen((aluc.io.alu_brancho & controlc.io.Branch)==="b0".U && controlc.io.NextPcSel==="b01".U){
			
		pc_main.io.input := pc_main.io.pc4

	}.elsewhen(controlc.io.NextPcSel==="b00".U){
			
		pc_main.io.input := pc_main.io.pc4

	}.otherwise{

		pc_main.io.input := DontCare
	
	}
     
	//pc_main.io.input := pc_main.io.pc4	
	
	//pc_main.io.input := io.muxj_out.asUInt

	io.output := aluc.io.alu_output


//memory
	mem_c.io.rs2:=reg32c.io.rs2_output
	mem_c.io.alu_out:=aluc.io.alu_output(9,2).asUInt
	mem_c.io.mem_read:=controlc.io.MemRead
	mem_c.io.mem_write:=controlc.io.MemWrite

	when (controlc.io.MemToReg=== 1.U){
		reg32c.io.write_data := mem_c.io.mem_out
	
	}.otherwise{reg32c.io.write_data := aluc.io.alu_output
	
	}
	

}
