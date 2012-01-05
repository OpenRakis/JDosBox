package jdos.win.builtin;

import jdos.cpu.CPU;
import jdos.cpu.CPU_Regs;
import jdos.cpu.Callback;
import jdos.hardware.Memory;
import jdos.win.builtin.dsound.IDirectSound;
import jdos.win.loader.BuiltinModule;
import jdos.win.loader.Loader;

public class DSound extends BuiltinModule {
    public DSound(Loader loader, int handle) {
        super(loader, "DSound.dll", handle);
        add(DirectSoundCreate);
    }

    // HRESULT DirectSoundCreate(LPCGUID lpGUID,LPDIRECTSOUND *ppDS,LPUNKNOWN pUnkOuter);
    private Callback.Handler DirectSoundCreate = new HandlerBase() {
        public String getName() {
            return "DSound.DirectSoundCreate";
        }
        public void onCall() {
            int lpGUID = CPU.CPU_Pop32();
            int ppDS = CPU.CPU_Pop32();
            int pUnkOuter = CPU.CPU_Pop32();
            Memory.mem_writed(ppDS, IDirectSound.create());
            CPU_Regs.reg_eax.dword = jdos.win.utils.Error.S_OK;
        }
    };
}
