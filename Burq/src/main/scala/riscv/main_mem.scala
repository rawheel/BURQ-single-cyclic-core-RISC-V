package riscv
//import chisel3.core.SInt
import chisel3._
import chisel3.util.Cat
//import chisel3.util.Fill
import chisel3.util.experimental.loadMemoryFromFile
class main_mem extends Module{
	val io = IO(new Bundle{
		val rs2 = Input(SInt(32.W))     //data
		val alu_out = Input(UInt(8.W))  //address
		val mem_read = Input(UInt(1.W))   //load
		val mem_write = Input(UInt(1.W))    //store
		val mem_out = Output(SInt(32.W))
})	
	io.mem_out:=0.S	
	val mem = Mem(1024,SInt(32.W))
	when(io.mem_write===1.U){
		mem.write(io.alu_out.asUInt,io.rs2.asSInt) 
	}.elsewhen(io.mem_read===1.U){
		//io.mem_out := mem.read(io.alu_out.asUInt,io.mem_read)
		io.mem_out := mem(io.alu_out.asUInt)			
	}
}		
