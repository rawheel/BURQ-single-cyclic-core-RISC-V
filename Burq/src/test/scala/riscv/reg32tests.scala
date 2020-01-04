package riscv

import chisel3.iotesters.PeekPokeTester

class reg32tests(c:reg32) extends PeekPokeTester(c){
	poke(c.io.imm_rs1, 1)
	poke(c.io.imm_rs2, 3)
	poke(c.io.rd_reg,3)
	poke(c.io.reg_write,1)
	
	



	step(1)
	expect(c.io.rs1_output,0)
	expect(c.io.rs2_output,0)
	


}
