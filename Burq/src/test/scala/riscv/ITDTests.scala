package riscv

import chisel3.iotesters.PeekPokeTester

class ITDTests(c: ITD) extends PeekPokeTester(c) {
	poke(c.io.opCode, 51)
	step(1)
	expect(c.io.RType, 1)
}
