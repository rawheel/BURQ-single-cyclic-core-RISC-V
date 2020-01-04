package riscv

import chisel3.iotesters.PeekPokeTester

class CDTests(c: CD) extends PeekPokeTester(c){
	poke(c.io.RType, 1)
	step(1)
	expect(c.io.RegWrite, 1)
}


