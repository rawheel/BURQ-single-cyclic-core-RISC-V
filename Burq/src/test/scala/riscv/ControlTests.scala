package riscv

import chisel3.iotesters.PeekPokeTester

class ControlTests(c: Control) extends PeekPokeTester(c){
	poke(c.io.OpCode, 55)
	step(1)
	expect(c.io.ExtendSel, 1)
}
