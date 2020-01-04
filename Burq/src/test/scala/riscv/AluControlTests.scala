package riscv

import chisel3.iotesters.PeekPokeTester

class AluControlTests(c: AluControl) extends PeekPokeTester(c){
	poke(c.io.aluopp, 0)
	poke(c.io.Func3, 0)
	poke(c.io.Func7, 0)
	step(1)
	expect(c.io.AluC, 0)
}
