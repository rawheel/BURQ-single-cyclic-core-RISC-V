package riscv

import chisel3.iotesters.PeekPokeTester

class alutests(c:alu) extends PeekPokeTester(c){
	poke(c.io.A, 13)
	poke(c.io.B, 13)
	poke(c.io.alu_c,16)
	step(1)
	
	expect(c.io.alu_output,1)
	expect(c.io.alu_brancho,1)
}

