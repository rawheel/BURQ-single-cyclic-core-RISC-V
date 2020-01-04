package riscv

import chisel3.iotesters.PeekPokeTester

class pctests(c: pc) extends PeekPokeTester(c) {
	poke(c.io.input, 8)
	step(1)
	step(1)
	//expect(c.io.pc4, 4)
	//expect(c.io.pc_output,0)
}
