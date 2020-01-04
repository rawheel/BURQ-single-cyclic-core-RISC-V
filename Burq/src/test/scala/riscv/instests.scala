package riscv

import chisel3.iotesters.PeekPokeTester

class instests(c: instruction) extends PeekPokeTester(c) {
	poke(c.io.write_address, 2)
	step(1)
	//expect(c.io.pc4, 4)
	//expect(c.io.pc_output,0)
}
