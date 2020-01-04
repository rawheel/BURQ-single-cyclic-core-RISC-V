package riscv

import chisel3.iotesters.PeekPokeTester

class conntests(c:conn) extends PeekPokeTester(c){
	//poke(c.io.instructions_c, 3273363)
	//poke(c.io.pc_c, 0)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	step(1)
	//expect(c.io.conn_output, )
}
