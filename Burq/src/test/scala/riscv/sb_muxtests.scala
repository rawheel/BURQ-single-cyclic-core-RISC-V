package riscv

import chisel3.iotesters.PeekPokeTester

class sb_muxtests(c: sb_mux) extends PeekPokeTester(c){
	step(1)
	expect(c.io.and_out,1)
	

}
