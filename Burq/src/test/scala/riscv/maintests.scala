package riscv

import chisel3.iotesters.PeekPokeTester

class maintests(c: main_mem) extends PeekPokeTester(c) {
	poke(c.io.rs2, 2)
	poke(c.io.alu_out, 4)
	poke(c.io.mem_read, 0)
	poke(c.io.mem_write, 1)
	step(1)
	//expect(c.io., 4)
	//expect(c.io.pc_output,0)
}
