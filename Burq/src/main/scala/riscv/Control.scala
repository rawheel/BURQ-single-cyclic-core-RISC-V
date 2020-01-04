package riscv

import chisel3._

class Control extends Module{
	val io = IO(new Bundle{
		val OpCode = Input(UInt(7.W))
		val MemWrite = Output(UInt(1.W))
		val Branch = Output(UInt(1.W))
		val MemRead = Output(UInt(1.W))
		val RegWrite = Output(UInt(1.W))
		val MemToReg = Output(UInt(1.W))
		val AluOp = Output(UInt(3.W))
		val Operand_aSel = Output(UInt(2.W))
		val Operand_bSel = Output(UInt(1.W))
		val ExtendSel = Output(UInt(2.W))
		val NextPcSel = Output(UInt(2.W))
	})

		val ID = Module(new ITD())
		val CD = Module(new CD())
		
		ID.io.opCode := io.OpCode


		CD.io.RType := ID.io.RType
		CD.io.Load := ID.io.Load
		CD.io.Store := ID.io.Store
		CD.io.SBType := ID.io.Branch
		CD.io.IType := ID.io.IType
		CD.io.Jalr := ID.io.Jalr
		CD.io.Jal := ID.io.Jal
		CD.io.Lui := ID.io.Lui
		
		io.MemWrite := CD.io.MemWrite
		io.Branch := CD.io.Branch
		io.MemRead := CD.io.MemRead
		io.RegWrite := CD.io.RegWrite
		io.MemToReg := CD.io.MemToReg
		io.AluOp := CD.io.AluOp
		io.Operand_aSel := CD.io.Operand_aSel
		io.Operand_bSel := CD.io.Operand_bSel
		io.ExtendSel := CD.io.ExtendSel
		io.NextPcSel := CD.io.NextPcSel


}
