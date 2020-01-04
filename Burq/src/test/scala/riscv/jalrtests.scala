package riscv

import chisel3.iotesters.PeekPokeTester

class jalrtests(c:jalr) extends PeekPokeTester(c) {
	poke(c.io.j_rs1, 1)
	poke(c.io. j_imm , 8)
	//poke(c.io.mem_read, 0)
	//poke(c.io.mem_write, 1)
	step(1)
	expect(c.io.jalr_output,8)
	//expect(c.io.pc_output,0)
}
