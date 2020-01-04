package riscv

import chisel3.iotesters.PeekPokeTester

class ImmediateTests(c: Immediate) extends PeekPokeTester(c){
	poke(c.io.instruction, 1048576)
	poke(c.io.pc, 0)
	step(1)
	expect(c.io.I_Type, 1)
	expect(c.io.S_Type, 0)
	expect(c.io.Sb_Type, 0)
	expect(c.io.Uj_Type, 2048)
	expect(c.io.U_Type, 1048576)

}
