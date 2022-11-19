import React from 'react';
import { AlertColor, Snackbar } from '@mui/material';
import MuiAlert, { AlertProps } from '@mui/material/Alert';

const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

interface ToastProps {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
  toastType: AlertColor;
  message: string;
}

export default function Toast({ isOpen, setIsOpen, toastType, message }: ToastProps) {
  const handleClose = (event?: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === 'clickaway') {
      return;
    }

    setIsOpen(false);
  };

  return (
    <Snackbar open={isOpen} autoHideDuration={4000} onClose={handleClose}>
      <Alert onClose={handleClose} severity={toastType} sx={{ width: '100%' }}>
        {message}
      </Alert>
    </Snackbar>
  );
}
